<template>
  <div></div>
</template>

<script>
import jwt_decode from "jwt-decode";
import keycloak from "../main";
export default {
  name: "LandingPage",
  mounted() {
    keycloak.updateToken(-1).success((refreshed)=>{
      if (refreshed) {
        console.log("Token forcibly refreshed");
        localStorage.setItem("user-token", keycloak.token);
        let decoded_user_token = jwt_decode(keycloak.token);
        console.log(decoded_user_token);
        if (decoded_user_token['realm_access']['roles'].includes('Teacher')) {
          this.$router.push({ path: '/teacher-courses' });
        }
        else if (decoded_user_token['realm_access']['roles'].includes('Student')) {
          this.$router.push({ path: '/student-courses' });
        }
        else {
          this.$router.push({ path: '/choose-role' });
        }
      } else {
        console.error('Token not forcibly refreshed');
        this.$router.push({ path: '/' });
      }

    }).error(()=>{
      console.error("Force refresh token error");
    });
  }
}
</script>

<style scoped>

</style>