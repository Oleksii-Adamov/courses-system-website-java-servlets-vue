
import Vue from 'vue'
import VueRouter from 'vue-router'
import App from './App.vue'
import { BootstrapVue } from 'bootstrap-vue'
import Keycloak from 'keycloak-js';
import Routes from './routes'
//import VueLogger from 'vuejs-logger';
// import router from "@/router";

Vue.config.productionTip = false

// const loggerOptions = {
//     isEnabled: true,
//     logLevel : Vue.config.productionTip  ? 'error' : 'debug',
//     stringifyArguments : false,
//     showLogLevel : true,
//     showMethodName : true,
//     separator: '|',
//     showConsoleColors: true
// };

let initOptions = {
    url: 'http://127.0.0.1:8080', realm: 'CoursesRealm', clientId: 'courses-app-client', onLoad:'login-required'
}

let keycloak = new Keycloak(initOptions);

keycloak.init({ onLoad: initOptions.onLoad }).success((auth) =>{

    if(!auth) {
        window.location.reload();
    } else {
        //this.$log.info("Authenticated");
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


    // Vue.use(VueLogger, loggerOptions);

    // TODO: Maybe dont store the token in the localstore, rather use it direct from the keycloak.token object
    //localStorage.setItem("vue-token", keycloak.token);
    //localStorage.setItem("vue-refresh-token", keycloak.refreshToken);

    setInterval(() =>{
        keycloak.updateToken(70).success((refreshed)=>{
            if (refreshed) {
           ///     Vue.$log.debug('Token refreshed');
                console.log("Token refreshed");
            } else {
   //             Vue.$log.warn('Token not refreshed, valid for '
    //                + Math.round(keycloak.tokenParsed.exp + keycloak.timeSkew - new Date().getTime() / 1000) + ' seconds');
                console.log('Token not refreshed, valid for '
                    + Math.round(keycloak.tokenParsed.exp + keycloak.timeSkew - new Date().getTime() / 1000) + ' seconds');
            }
        }).error(()=>{
            //Vue.$log.error('Failed to refresh token');
            console.error("Authenticated");
        });


    }, 60000)

}).error(() =>{
    console.error("Authentication Failed");
    //Vue.$log.error("Authenticated Failed");
});


// new Vue({
//     render: h => h(App),
// }).$mount('#app')
//
// Vue.use(BootstrapVue);
