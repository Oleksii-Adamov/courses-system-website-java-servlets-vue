<template>
<div>
  <CoursesItem :courses="courses"/>
</div>
</template>

<script>
import {getStudentCourses} from '@/services/CourseService'
import CoursesItem from "@/components/CoursesItem";
export default {
  name: "StudentCoursesPage",
  components: {CoursesItem},
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