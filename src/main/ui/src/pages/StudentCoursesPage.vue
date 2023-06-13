<template>
<div>
  <StudentNavigation></StudentNavigation>
  <CoursesItem :courses="courses"/>
</div>
</template>

<script>
import {getStudentCourses} from '@/services/CourseService'
import CoursesItem from "@/components/CoursesItem";
import StudentNavigation from "@/components/StudentNavigation";
export default {
  name: "StudentCoursesPage",
  components: {StudentNavigation, CoursesItem},
  data() {
    return {
      courses: []
    }
  },
  methods: {
    getStudentCourses() {
      getStudentCourses().then(response => {
        console.log(response)
        this.courses = response
      })
    },
  },
  mounted() {
    this.getStudentCourses();
  }
}
</script>