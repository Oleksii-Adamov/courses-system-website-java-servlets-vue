import keycloak from "./main";
import Vue from "vue";
import VueRouter from "vue-router";
import Routes from "@/routes";
import VueResource from "vue-resource";
import App from "@/App";
import {BootstrapVue} from "bootstrap-vue";
const EventEmitter = require('events');

export const bus = new EventEmitter();

export function initKeycloak() {
    keycloak.init({ onLoad: 'login-required' }).then((auth) =>{

        if(!auth) {
            window.location.reload();
        } else {
            console.log("Authenticated");
        }

        Vue.use(VueRouter);
        const router = new VueRouter({
            routes: Routes,
            mode: 'history'
        });

        Vue.use(VueResource);

        new Vue({
            router,
            render: h => h(App),
        }).$mount('#app')

        Vue.use(BootstrapVue);

        localStorage.setItem("user-token", keycloak.token);
        console.log(keycloak.token);

        bus.emit('unlocked');

        setInterval(() =>{
            whenTokenNotUndefined().then( () => {
                keycloak.updateToken(70).then((refreshed) => {
                    if (refreshed) {
                        localStorage.setItem("user-token", keycloak.token);
                        console.log("Token refreshed");
                        console.log(keycloak.token);
                    } else {
                        console.log('Token not refreshed, valid for '
                            + Math.round(keycloak.tokenParsed.exp + keycloak.timeSkew - new Date().getTime() / 1000) + ' seconds');
                    }
                }).catch(() => {
                    console.error("Refresh token error");
                });
            });

        }, 60000);

    }).catch(() =>{
        console.error("Authentication Failed");
    });
}
export async function whenTokenNotUndefined() {
    if (localStorage.getItem('user-token') === undefined) {
        console.log("waiting")
        await new Promise(resolve => bus.once('unlocked', resolve));
    }
}
export async function authorizationHeaders() {
    let headers = new Headers();
    await whenTokenNotUndefined();
    headers.append("Authorization", "Bearer " + localStorage.getItem('user-token'));
    console.log("Sending ", localStorage.getItem('user-token'));
    return headers;
}
export function logout() {
    keycloak.logout({redirectUri: 'http://localhost:8081/'});
}