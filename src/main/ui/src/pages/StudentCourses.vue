<template>
<div>
  <Courses :courses="courses"/>
</div>
</template>

<script>
import {getStudentCourses} from '@/services/CourseService'
import Courses from "@/components/Courses";
export default {
  name: "StudentCourses",
  components: {Courses},
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