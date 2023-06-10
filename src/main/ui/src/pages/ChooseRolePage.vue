<template>
  <div>
  <iframe name="hiddenFrame" width="0" height="0" border="0" style="display: none;"></iframe>
<!--  <form action="/api/user/add-role" method="POST" id = "roleForm">-->
<!--  <form id = "roleForm">-->
  <form action="http://localhost:3000/Lab1/api/user/add-role" method="POST" id = "roleForm" target="hiddenFrame">
    <input type="hidden" name = "userId" id = "userId" :value=getUserId()>
    <label>Choose role</label>
    <select name = "role" id = "role" v-model="role">
      <option disabled value="">Please select one</option>
      <option value="Teacher">Teacher</option>
      <option value="Student">Student</option>
    </select>
<!--    <button @click="submitRoleChange">Confirm</button>-->
<!--    <button id = "submitRoleChangeButton">Confirm</button>-->
    <div>
      <input type="submit" value="Confirm">
    </div>
  </form>
  </div>
</template>

<script>
import jwt_decode from "jwt-decode";
export default {
  name: "ChooseRolePage",
  data() {
    return {
      role: ""
    };
  },
  methods: {
    // submitRoleChange() {
    //   submitRoleChangejs(this.role);
    // },
    getUserId() {
      return jwt_decode(localStorage.getItem('user-token'))['sub'];
    },
    async submitRoleChange() {
      console.log("submitRoleChange");
      // this.$http
      //     .post(
      //         "/api/user/add-role?userId=" +
      //         //"http://localhost:3000/Lab1/user/add-role?userId=" +
      //         this.getUserId() +
      //         "&role=" +
      //         this.role
      //     )
      //     .then((response) => {
      //       console.log("submitRoleChange response status " + response.status);
      //     });
      // const url = "/api/user/add-role?userId=" + this.getUserId() + "&role=" + this.role;
      const url = "http://localhost:3000/Lab1/api/user/add-role?userId=" + this.getUserId() + "&role=" + this.role;
      const response = await fetch(url, {
        method: "POST"});
      console.log("submitRoleChange response status " + response.status);
    }
  }
}
// function getUserId() {
//   return jwt_decode(localStorage.getItem('user-token'))['sub'];
// }
// async function submitRoleChangejs(role) {
//   console.log("submitRoleChange");
//   const url = "/api/user/add-role?userId=" + this.getUserId() + "&role=" + role;
//   const response = await fetch(url, {
//     method: "POST"});
//   console.log(response);
//   console.log("submitRoleChange response status " + response.status);
// }
//document.getElementById("submitRoleChangeButton").addEventListener("click", () => {submitRoleChange(this.role)});
//
// window.addEventListener("load", () => {
//   function sendData() {
//     const XHR = new XMLHttpRequest();
//
//     // Bind the FormData object and the form element
//     const FD = new FormData(form);
//
//     // Define what happens on successful data submission
//     XHR.addEventListener("load", (event) => {
//       alert(event.target.responseText);
//     });
//
//     // Define what happens in case of error
//     XHR.addEventListener("error", () => {
//       alert("Oops! Something went wrong.");
//     });
//
//     // Set up our request
//     XHR.open("POST", "/api/add-user-role");
//
//     // The data sent is what the user provided in the form
//     XHR.send(FD);
//   }
//
//   // Get the form element
//   const form = document.getElementById("roleForm");
//
//   // Add 'submit' event handler
//   form.addEventListener("submit", (event) => {
//     event.preventDefault();
//
//     sendData();
//   });
// });

</script>

<style scoped>

</style>