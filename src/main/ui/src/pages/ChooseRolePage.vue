<template>
  <div>
<!--  <iframe name="hiddenFrame" width="0" height="0" border="0" style="display: none;"></iframe>-->
  <form action="http://localhost:3000/Lab1/api/user/add-role" method="POST" id = "roleForm">
    <input type="hidden" name = "userId" id = "userId" :value=getUserId()>
    <label>Choose role</label>
    <select name = "role" id = "role">
      <option disabled value="">Please select one</option>
      <option value="Teacher">Teacher</option>
      <option value="Student">Student</option>
    </select>
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
  methods: {
    getUserId() {
      return jwt_decode(localStorage.getItem('user-token'))['sub'];
    }
  }
}

</script>

<style scoped>

</style>