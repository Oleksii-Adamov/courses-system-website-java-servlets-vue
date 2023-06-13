<template>
  <div>
<!--  <iframe name="hiddenFrame" width="0" height="0" border="0" style="display: none;"></iframe>-->
<!--  <form action="http://localhost:3000/Lab1/api/user/add-role" method="POST" id = "roleForm">-->
<!--    <input type="hidden" name = "userId" id = "userId" :value=getUserIdMethod()>-->
<!--    <form>-->
      <label>Choose role</label>
      <select name = "role" id = "role" v-model="role">
        <option disabled value="">Please select one</option>
        <option value="Teacher">Teacher</option>
        <option value="Student">Student</option>
      </select>
<!--      <div>-->
<!--        <input type="submit" value="Confirm">-->
<!--      </div>-->
      <button @click="submitRoleChange">Confirm</button>
<!--    </form>-->
  </div>
</template>

<script>
// import getUserId from "@/authUtils";
import axios from "axios";
import {whenTokenNotUndefined} from "@/authUtils";

export default {
  name: "ChooseRolePage",
  data() {
    return {
      role: "none"
    }
  },
  methods: {
    // getUserIdMethod() {
    //   return getUserId();
    // }
    submitRoleChange() {
      whenTokenNotUndefined().then( () => {
        console.log(this.role);
        axios({
          method: 'post',
          url: '/api/user/add-role?role='+this.role,
          // url: '/api/user/add-role',
          // data: {
          //   role: this.role
          // },
          headers: {'Authorization': "Bearer " + localStorage.getItem('user-token')}
        }).then((response) => {console.log(response); this.$router.push({ path: '/' });}).catch((error) => {
          if (error.response) {
            // The request was made and the server responded with a status code
            // that falls out of the range of 2xx
            console.error(error.response.data);
            console.error(error.response.status);
            console.error(error.response.headers);
          } else if (error.request) {
            // The request was made but no response was received
            // `error.request` is an instance of XMLHttpRequest in the browser and an instance of
            // http.ClientRequest in node.js
            console.error("The request was made but no response was received ", error.request);
          } else {
            // Something happened in setting up the request that triggered an Error
            console.error(error.message);
          }
          console.log(error.config);
        });
      });
    }
  }
  // mounted() {
  //   //axios.post("http://localhost:3000/Lab1/api/user/add-role")
  //   // axios.post("/api/user/add-role")
  //   //     .then(response => console.log(response));
  //   // whenTokenNotUndefined().then( () => {
  //   //   axios({
  //   //     method: 'post',
  //   //     url: '/api/user/add-role',
  //   //     data: {
  //   //       role: this.role
  //   //     },
  //   //     headers: {'Authorization': "Bearer " + localStorage.getItem('user-token')}
  //   //   });
  //   // });
  // }
}

</script>

<style scoped>

</style>