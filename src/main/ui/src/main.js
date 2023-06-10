
import Vue from 'vue'
import VueRouter from 'vue-router'
import App from './App.vue'
import { BootstrapVue } from 'bootstrap-vue'
import Keycloak from 'keycloak-js';
import Routes from './routes'
import VueResource from 'vue-resource'

Vue.config.productionTip = false


let initOptions = {
    url: 'http://127.0.0.1:8080', realm: 'CoursesRealm', clientId: 'courses-app-client', onLoad:'login-required'
}

let keycloak = new Keycloak(initOptions);

//let realm = keycloak.realm(realm);

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
    });

    Vue.use(VueResource);

    new Vue({
        router,
        render: h => h(App),
    }).$mount('#app')

    Vue.use(BootstrapVue);

    //console.log(keycloak.realm('CoursesRealm'))
    //.users().get('6fd45d53-4296-4f53-b34e-b62356c6dc8a')
    localStorage.setItem("user-token", keycloak.token);

    setInterval(() =>{
        keycloak.updateToken(70).success((refreshed)=>{
            if (refreshed) {
                console.log("Token refreshed");
                localStorage.setItem("user-token", keycloak.token);
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