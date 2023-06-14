<template>
  <div>
    <TeacherNavigation></TeacherNavigation>
    <h1>Grade student {{this.studentFullName}}</h1>
    <div>
      <label>Grade</label>
      <input type="number" min="0" max = "1000" v-model="grade">
      <label>Your response</label>
      <input v-model="teacherResponse">
      <button @click="submitGradeStudent">Create</button>
    </div>
  </div>
</template>

<script>
import TeacherNavigation from "@/components/TeacherNavigation";
import postRequest from "@/services/postRequest";
export default {
  name: "GradeStudentPage",
  components: {TeacherNavigation},
  data() {
    return {
      studentUserId: "",
      studentFullName: "",
      courseId: "",
      courseName: "",
      courseMaxGrade: "",
      grade: "",
      teacherResponse: ""
    }
  },
  methods: {
    submitGradeStudent() {
      if (this.studentUserId !== "" && this.studentFullName !== "" && this.courseId !== "" && this.courseName !== "" && this.courseMaxGrade !== "") {
        if (this.grade !== "" && this.teacherResponse !== "") {
          postRequest('/api/courses/grade-student?studentUserId=' + this.studentUserId + '&courseId=' + this.courseId
              + '&grade=' + this.grade + '&teacherResponse=' + this.teacherResponse, () => {
            this.$router.push({ name: 'teacher-course', params: { id: this.courseId, name: this.courseName, maxGrade: this.courseMaxGrade} });
          });
        } else {
          alert("All field must be filled");
        }
      } else {
        alert("No data passed to this page, please return to Your courses and try again");
      }
    }
  },
  mounted() {
    this.studentUserId = this.$route.params.studentUserId;
    this.studentFullName = this.$route.params.studentFullName;
    this.courseId = this.$route.params.courseId;
    this.courseName = this.$route.params.courseName;
    this.courseMaxGrade = this.$route.params.courseMaxGrade;
  }
}
</script>

<style scoped>

</style>