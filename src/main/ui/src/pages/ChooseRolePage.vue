<template>
  <div>
      <label>Choose role</label>
      <select name = "role" id = "role" v-model="role">
        <option disabled value="">Please select one</option>
        <option value="Teacher">Teacher</option>
        <option value="Student">Student</option>
      </select>
      <button @click="submitRoleChange">Confirm</button>
  </div>
</template>

<script>
import postRequest from "@/services/postRequest";

export default {
  name: "ChooseRolePage",
  data() {
    return {
      role: ""
    }
  },
  methods: {
    submitRoleChange() {
      if (this.role !== "") {
        postRequest('/api/user/add-role?role=' + this.role, () => {
          this.$router.push({path: '/'});
        });
      } else {
        alert("Choose role");
      }
    }
  }
}

</script>

<style scoped>

</style>