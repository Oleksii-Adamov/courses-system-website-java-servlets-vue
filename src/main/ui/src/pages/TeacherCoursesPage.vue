<template>
  <div>
    <TeacherNavigation></TeacherNavigation>
    <div>
      <div class="container">
        <h2>Your courses</h2>
        <ul>
          <li v-for="item in courses" :key="item.id">
            <router-link :to="{ name: 'teacher-course', params: { id: item.id, name: item.name, maxGrade: item.maxGrade} }"> {{ item.name }} </router-link>
          </li>
        </ul>
      </div>
    </div>
  </div>
</template>

<script>
import {getTeacherCourses} from "@/services/CourseService";
import TeacherNavigation from "@/components/TeacherNavigation";
export default {
  name: "TeacherCoursesPage",
  components: {TeacherNavigation},
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