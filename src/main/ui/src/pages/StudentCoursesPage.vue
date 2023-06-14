<template>
<div>
  <StudentNavigation></StudentNavigation>
  <div class="container">
    <h2>Your courses</h2>
    <ul>
      <li v-for="item in courses" :key="item.id">
        <router-link :to="{ name: 'student-course', params: { id: item.id, courseName: item.name} }"> {{ item.name }} </router-link>
      </li>
    </ul>
  </div>
</div>
</template>

<script>
import {getStudentCourses} from '@/services/CourseService'
import StudentNavigation from "@/components/StudentNavigation";
export default {
  name: "StudentCoursesPage",
  components: {StudentNavigation},
  data() {
    return {
      courses: []
    }
  },
  methods: {
    getStudentCourses() {
      getStudentCourses().then(response => {
        console.log(response);
        this.courses = response;
      })
    },
  },
  mounted() {
    this.getStudentCourses();
  }
}
</script>