<template>
  <nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
      <div class="navbar-nav">
        <a class="nav-item nav-link" href="/teacher-courses">Your courses</a>
        <a class="nav-item nav-link" href="/create-course">Create course</a>
        <a class="nav-item nav-link" @click="logoutMethod()">Logout</a>
      </div>
    </div>
  </nav>
</template>

<script>
import {logout} from "@/authUtils";

export default {
  name: "TeacherNavigation",
  methods: {
    logoutMethod() {
      logout();
    }
  }
}
</script>

<style scoped>

</style>