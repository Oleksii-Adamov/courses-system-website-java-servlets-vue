<template>
  <div>
    <label>Course name</label>
    <input v-model="courseName">
    <label>Max grade</label>
    <input type="number" min="0" max = "1000" v-model="maxGrade">
    <button @click="submitCreateCourse">Create</button>
  </div>
</template>

<script>
import postRequest from "@/services/postRequest";

export default {
  name: "CreateCoursePage",
  data() {
    return {
      courseName: "",
      maxGrade: ""
    }
  },
  methods: {
    submitCreateCourse() {
      if (this.courseName !== "" && this.maxGrade !== "") {
        postRequest('/api/courses/create?name=' + this.courseName + "&maxGrade=" + this.maxGrade, () => {
          this.$router.push({path: '/teacher-courses'});
        });
      } else {
        alert("All field must be field");
      }
    }
  }
}
</script>

<style scoped>

</style>