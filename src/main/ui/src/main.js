
import Vue from 'vue'
import Keycloak from 'keycloak-js';
import {initKeycloak} from "@/authUtils";

Vue.config.productionTip = false

let keycloakInitOptions = {
    url: 'http://127.0.0.1:8080', realm: 'CoursesRealm', clientId: 'courses-app-client', onLoad:'login-required'
}

let keycloak = new Keycloak(keycloakInitOptions);
export default keycloak;
initKeycloak();
