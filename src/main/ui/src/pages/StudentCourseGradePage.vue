<template>
  <div>
    <StudentNavigation></StudentNavigation>
    <h1>{{this.courseName}}</h1>
    <h2>Grade: {{grade}}/{{maxGrade}}</h2>
    <h2>Teacher response:</h2>
    <div>{{this.teacherResponse}}</div>
  </div>
</template>

<script>
import { getStudentGrade} from "@/services/CourseService";
import StudentNavigation from "@/components/StudentNavigation";

export default {
  name: "StudentCourseGradePage",
  components: {StudentNavigation},
  data() {
    return {
      courseName: "",
      maxGrade: "",
      grade: "",
      teacherResponse: ""
    }
  },
  props:{
    id:{
      type: Number,
      required: true,
    }
  },
  mounted() {
    console.log("props id ", this.id);
    this.courseName = this.$route.params.courseName;
    getStudentGrade(this.id).then(response => {
      this.maxGrade = response.maxGrade;
      this.grade = response.grade;
      this.teacherResponse = response.teacherResponse;
    });
  }
}
</script>

<style scoped>

</style>