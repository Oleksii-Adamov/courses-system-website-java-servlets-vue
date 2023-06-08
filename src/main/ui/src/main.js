
import Vue from 'vue'
import VueRouter from 'vue-router'
import App from './App.vue'
import { BootstrapVue } from 'bootstrap-vue'
import Keycloak from 'keycloak-js';
import Routes from './routes'

Vue.config.productionTip = false


let initOptions = {
    url: 'http://127.0.0.1:8080', realm: 'CoursesRealm', clientId: 'courses-app-client', onLoad:'login-required'
}

let keycloak = new Keycloak(initOptions);

keycloak.init({ onLoad: initOptions.onLoad }).success((auth) =>{

    if(!auth) {
        window.location.reload();
    } else {
        console.log("Authenticated");
    }

    Vue.use(VueRouter);
    const router = new VueRouter({
        routes: Routes,
        mode: 'history'
    })
    new Vue({
        router,
        render: h => h(App),
    }).$mount('#app')

    Vue.use(BootstrapVue);

    // TODO: Maybe dont store the token in the localstore, rather use it direct from the keycloak.token object
    //localStorage.setItem("vue-token", keycloak.token);
    //localStorage.setItem("vue-refresh-token", keycloak.refreshToken);

    setInterval(() =>{
        keycloak.updateToken(70).success((refreshed)=>{
            if (refreshed) {
                console.log("Token refreshed");
            } else {
                console.log('Token not refreshed, valid for '
                    + Math.round(keycloak.tokenParsed.exp + keycloak.timeSkew - new Date().getTime() / 1000) + ' seconds');
            }
        }).error(()=>{
            console.error("Authenticated");
        });


    }, 60000)

}).error(() =>{
    console.error("Authentication Failed");
});