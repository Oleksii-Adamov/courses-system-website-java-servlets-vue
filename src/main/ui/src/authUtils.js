// import jwt_decode from "jwt-decode";
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
    keycloak.init({ onLoad: 'login-required' }).success((auth) =>{

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
                localStorage.setItem("user-token", undefined);
                keycloak.updateToken(70).success((refreshed) => {
                    if (refreshed) {
                        console.log("Token refreshed");
                        localStorage.setItem("user-token", keycloak.token);
                        bus.emit('unlocked');
                        console.log(keycloak.token);
                    } else {
                        console.log('Token not refreshed, valid for '
                            + Math.round(keycloak.tokenParsed.exp + keycloak.timeSkew - new Date().getTime() / 1000) + ' seconds');
                    }
                }).error(() => {
                    console.error("Refresh token error");
                });
            });

        }, 60000)

        // setInterval(() =>{
        //     keycloak.updateToken(70).success((refreshed)=>{
        //         if (refreshed) {
        //             console.log("Token refreshed");
        //             localStorage.setItem("user-token", keycloak.token);
        //             console.log(keycloak.token);
        //         } else {
        //             console.log('Token not refreshed, valid for '
        //                 + Math.round(keycloak.tokenParsed.exp + keycloak.timeSkew - new Date().getTime() / 1000) + ' seconds');
        //         }
        //     }).error(()=>{
        //         console.error("Refresh token error");
        //     });
        //
        //
        // }, 60000)

    }).error(() =>{
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
    // forciblyRefreshToken();
    await whenTokenNotUndefined();
    headers.append("Authorization", "Bearer " + localStorage.getItem('user-token'));
    console.log("Sending ", localStorage.getItem('user-token'));
    return headers;
}
// export async function forciblyRefreshToken() {
//     await whenTokenNotUndefined();
//     //localStorage.setItem('user-token', undefined);
//     keycloak.updateToken(-1).then((refreshed)=>{
//         if (refreshed) {
//             console.log("Token forcibly refreshed");
//             localStorage.setItem("user-token", keycloak.token);
//             //bus.emit('unlocked');
//             let decoded_user_token = jwt_decode(keycloak.token);
//             console.log(decoded_user_token);
//             if (decoded_user_token['realm_access']['roles'].includes('Teacher')) {
//                 this.$router.push({ path: '/teacher-courses' });
//             }
//             else if (decoded_user_token['realm_access']['roles'].includes('Student')) {
//                 this.$router.push({ path: '/student-courses' });
//             }
//             else {
//                 this.$router.push({ path: '/choose-role' });
//             }
//         } else {
//             console.error('Token not forcibly refreshed');
//             this.$router.push({ path: '/' });
//         }
//
//     }).catch(()=>{
//         console.error("Force refresh token error");
//         // this.$router.push({ path: '/' });
//         //window.location.reload();
//     });
//
// }
//
// function getUserId() {
//     return jwt_decode(localStorage.getItem('user-token'))['sub'];
// }