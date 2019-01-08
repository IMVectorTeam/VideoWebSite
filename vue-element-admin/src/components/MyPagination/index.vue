<template>
  <section>
    <div class="pagination-container" style="margin-top: 15px;">
      <el-pagination
        v-if="appearance === 1"
        :current-page.sync="page"
        :page-sizes="[5, 10, 15, 20, 30, 50]"
        :page-size="limit"
        :layout="layout"
        :total="total"
        background
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"/>
      <el-pagination
        v-if="appearance === 2"
        :current-page.sync="page"
        :page-size="limit"
        :pager-count="11"
        :total="total"
        layout="prev, pager, next"
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"/>
    </div>
  </section>
</template>

<script>
export default {
  name: 'MyPagination',
  props: {
    appearance: {
      type: Number,
      default: 1
    },
    type: {
      type: String,
      default: ''
    },
    baseType: {
      type: String,
      default: ''
    },
    layout: {
      type: String,
      default: 'total, sizes, prev, pager, next, jumper'
    },
    config: {
      type: Object,
      default: () => {}
    },
    initFunc: {
      type: Function,
      default: () => 1
    }
  },
  computed: {
    queryString() {
      console.log('----------------', `${this.baseType}/${this.type}ListQuery`)
      return `${this.baseType}/${this.type}ListQuery`
    },
    getListQueryString() {
      console.log('查询', `${this.baseType}/get${this.type[0].toUpperCase()}${this.type.slice(1)}List`)
      return `${this.baseType}/get${this.type[0].toUpperCase()}${this.type.slice(1)}List`
    },
    setListQueryString() {
      console.log('设置查询参数', `${this.baseType}/set${this.type[0].toUpperCase()}${this.type.slice(1)}ListQuery`)
      return `${this.baseType}/set${this.type[0].toUpperCase()}${this.type.slice(1)}ListQuery`
    },
    page: {
      get() {
        console.log('当前页码', this.$store.getters[this.queryString].page)
        return this.$store.getters[this.queryString].page
      },
      set(val) {}
    },
    limit: {
      get() {
        console.log('每页数量', this.$store.getters[this.queryString].limit)
        return this.$store.getters[this.queryString].limit
      },
      set(val) {}
    },
    total: {
      get() {
        console.log('总数量', this.$store.getters[this.queryString].total)
        return this.$store.getters[this.queryString].total
      },
      set(val) {}
    }
  },
  methods: {
    handleSizeChange(val) {
    //   console.log(this.getListQueryString.toString())
      this.$store.commit(this.setListQueryString.toString(), { usingKey: true, k: 'limit', v: val })
      if (this.initFunc() === 1) return this.$store.dispatch(this.getListQueryString.toString(), this.config)
    },
    handleCurrentChange(val) {
    //   console.log(this.getListQueryString.toString())
    //   console.log(this.setListQueryString.toString())
      this.$store.commit(this.setListQueryString.toString(), { usingKey: true, k: 'page', v: val })
      if (this.initFunc() === 1) return this.$store.dispatch(this.getListQueryString.toString(), this.config)
    }
  }
}
</script>

<style scoped>

</style>
