<template>
  <div>
    <TeacherNavigation></TeacherNavigation>
    <div>
      <CoursesItem :courses="courses"/>
    </div>
  </div>
</template>

<script>
import {getTeacherCourses} from "@/services/CourseService";
import CoursesItem from "@/components/CoursesItem";
import TeacherNavigation from "@/components/TeacherNavigation";
export default {
  name: "TeacherCoursesPage",
  components: {TeacherNavigation, CoursesItem},
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