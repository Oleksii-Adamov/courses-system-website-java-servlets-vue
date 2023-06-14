<template>
    <div class="container">
        <h2>Your courses</h2>
        <ul>
          <li v-for="item in courses" :key="item.id">
            <router-link :to="{ name: 'teacher-course', params: { id: item.id, name: item.name, maxGrade: item.maxGrade} }"> {{ item.name }} </router-link>
          </li>
        </ul>
    </div>
</template>

<script>

    export default {
        name: 'CoursesItem',
        props: ['courses']
     }
</script>