<template>
  <div
    :contenteditable="canEdit"
    class="textArea"
    @focus="isLocked = true"
    @blur="isLocked = false"
    @input="changeText"
    v-html="innerText"/>
</template>
<script type="text/ecmascript-6">
export default{
  name: 'TextArea',
  props: {
    value: {
      type: String,
      default: ''
    },
    canEdit: {
      type: Boolean,
      default: true
    }
  },
  data() {
    return {
      innerText: this.value,
      isLocked: false
    }
  },
  watch: {
    'value'() {
      if (!this.isLocked && !this.innerText) {
        this.innerText = this.value
      }
    }
  },
  methods: {
    changeText() {
      this.$emit('input', this.$el.innerHTML)
    }
  }
}
</script>
<style scoped>
    /* // .edit-div {
    //     width: 100%;
    //     height: 100%;
    //     overflow: auto;
    //     word-break: break-all;
    //     outline: none;
    //     user-select: text;
    //     white-space: pre-wrap;
    //     text-align: left;
    //     &[contenteditable=true]{
    //         user-modify: read-write-plaintext-only;
    //         &:empty:before {
    //             content: attr(placeholder);
    //             display: block;
    //             color: #ccc;
    //         }
    //     }
    // } */
.textArea{
  font-size: 12px;
  display: inline-block;
  box-sizing: border-box;
  background-color: #f4f5f7;
  border: 1px solid #e5e9ef;
  overflow: auto;
  border-radius: 4px;
  color: #555;
  width: 100%!important;
  height: 65px;
  transition: 0s;
  padding: 5px 10px;
  line-height: normal;
}
div:empty:before{
  content: attr(placeholder);
  color:#bbb;
}
div:focus:before{
  content:none;
}
</style>
