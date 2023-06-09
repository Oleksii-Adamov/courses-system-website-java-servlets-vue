<template>
  <div></div>
</template>

<script>
import jwt_decode from "jwt-decode";
export default {
  name: "LandingPage",
  mounted() {
    console.log(localStorage.getItem('user-token'));
    let decoded_user_token = jwt_decode(localStorage.getItem('user-token'));
    console.log(decoded_user_token);
    console.log(decoded_user_token['realm_access']['roles']);
    console.log(decoded_user_token['realm_access']['roles'].includes('NoRole'));
    console.log(decoded_user_token['realm_access']['roles'].includes('Teacher'));
    this.$router.push({ path: '/choose-role' });
  }
}
</script>

<style scoped>

</style>