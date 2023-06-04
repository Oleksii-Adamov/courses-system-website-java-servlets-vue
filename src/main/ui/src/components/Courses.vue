<template>
    <div class="container">
        <h2>Courses</h2>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>Name</th>
            </tr>
            </thead>
            <tbody>
              <tr v-for="item in courses" :key="item.id">
                  <td>{{ item.id }}</td>
              </tr>
            </tbody>
        </table>
    </div>
</template>

<script>

    export default {
        name: 'Courses',
        props: ['courses']
     }
</script>