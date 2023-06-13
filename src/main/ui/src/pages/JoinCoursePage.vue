<template>
  <div>
    <label>Course id</label>
    <input type="number" v-model="courseId">
    <button @click="submitJoinCourse">Join</button>
  </div>
</template>

<script>
import postRequest from "@/services/postRequest";

export default {
  name: "JoinCoursePage",
  data() {
    return {
      courseId: ""
    }
  },
  methods: {
    submitJoinCourse() {
      if (this.courseId !== "") {
        postRequest('/api/courses/join?id=' + this.courseId, () => {
          this.$router.push({path: '/student-courses'});
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