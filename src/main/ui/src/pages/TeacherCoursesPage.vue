<template>
  <div>
    <CoursesItem :courses="courses"/>
  </div>
</template>

<script>
import {getTeacherCourses} from "@/services/CourseService";
import CoursesItem from "@/components/CoursesItem";
export default {
  name: "TeacherCoursesPage",
  components: {CoursesItem},
  data() {
    return {
      courses: []
    }
  },
  methods: {
    getTeacherCourses() {
      getTeacherCourses().then(response => {
        console.log(response)
        this.courses = response
      })
    },
  },
  mounted() {
    this.getTeacherCourses();
  }
}
</script>

<style scoped>

</style>