<template>
  <div>
    <TeacherNavigation></TeacherNavigation>
    <h1>{{this.name}}</h1>
    <h2>Course id: {{id}}</h2>
    <h2>Max grade:{{this.maxGrade}} </h2>
    <h2>Students:</h2>
    <ul>
      <li v-for="item in this.students" :key="item.userId">
        <router-link :to="{ name: 'grade-student', params: { studentUserId: item.userId, studentFullName: item.fullName, courseId: id, courseName: name, courseMaxGrade: maxGrade} }"> {{ item.fullName }} </router-link>
      </li>
    </ul>
  </div>
</template>

<script>
import {getCourseStudents} from "@/services/CourseService";
import TeacherNavigation from "@/components/TeacherNavigation";
export default {
  name: "TeacherCourse",
  components: {
    TeacherNavigation
  },
  data() {
    return {
      name: "",
      maxGrade: "",
      students: []
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
    this.name = this.$route.params.name;
    this.maxGrade = this.$route.params.maxGrade;
    getCourseStudents(this.id).then(response => {
      console.log("getCourseStudents response " + response);
      this.students = response;
    });
  }
}
</script>
<style scoped>

</style>