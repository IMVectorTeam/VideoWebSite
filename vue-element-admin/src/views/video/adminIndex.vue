<template>
  <div class="app-container">
    <el-row :gutter="10">
      <el-col :span="8">
        <el-card :body-style="{ padding: '0px' }">
          <el-upload
            v-if="flag_1"
            class="upload-demo"
            drag
            action="/api/VideoWebSite/image/upload/0"
            multiple>
            <i class="el-icon-upload"/>
            <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
            <div slot="tip" class="el-upload__tip">只能上传jpeg文件，且不超过500kb</div>
          </el-upload>
          <img v-else src="http://127.0.0.1:8080/VideoWebSite/resources/carouselImage/0.jpeg" class="image">
          <div style="padding: 14px;">
            <div class="bottom clearfix">
              <el-button type="primary" mini style="float:right" @click="flag_1= !flag_1">修改</el-button>
            </div>
          </div>
        </el-card>
      </el-col>
      <el-col :span="8">
        <el-card :body-style="{ padding: '0px' }">
          <el-upload
            v-if="flag_2"
            class="upload-demo"
            drag
            action="/api/VideoWebSite/image/upload/1"
            multiple>
            <i class="el-icon-upload"/>
            <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
            <div slot="tip" class="el-upload__tip">只能上传jpeg文件，且不超过500kb</div>
          </el-upload>
          <img v-else src="http://127.0.0.1:8080/VideoWebSite/resources/carouselImage/1.jpeg" class="image">
          <div style="padding: 14px;">
            <div class="bottom clearfix">
              <el-button type="primary" mini style="float:right" @click="flag_2= !flag_2">修改</el-button>
            </div>
          </div>

        </el-card>
      </el-col >
      <el-col :span="8">
        <el-card :body-style="{ padding: '0px' }">
          <el-upload
            v-if="flag_3"
            class="upload-demo"
            drag
            action="/api/VideoWebSite/image/upload/2"
            multiple>
            <i class="el-icon-upload"/>
            <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
            <div slot="tip" class="el-upload__tip">只能上传jpeg文件，且不超过500kb</div>
          </el-upload>
          <img v-else src="http://127.0.0.1:8080/VideoWebSite/resources/carouselImage/2.jpeg" class="image">
          <div style="padding: 14px;">
            <div class="bottom clearfix">
              <el-button type="primary" mini style="float:right" @click="flag_3= !flag_3">修改</el-button>
            </div>
          </div>
        </el-card>
      </el-col >
    </el-row>
    <el-row :gutter="10">
      <el-col :span="8">
        <el-card :body-style="{ padding: '0px' }">
          <el-upload
            v-if="flag_4"
            class="upload-demo"
            drag
            action="/api/VideoWebSite/image/upload/3"
            multiple>
            <i class="el-icon-upload"/>
            <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
            <div slot="tip" class="el-upload__tip">只能上传jpeg文件，且不超过500kb</div>
          </el-upload>
          <img v-else src="http://127.0.0.1:8080/VideoWebSite/resources/carouselImage/3.jpeg" class="image">
          <div style="padding: 14px;">
            <div class="bottom clearfix">
              <el-button type="primary" mini style="float:right" @click="flag_4= !flag_4">修改</el-button>
            </div>
          </div>
        </el-card>
      </el-col>
      <el-col :span="8">
        <el-card :body-style="{ padding: '0px' }">
          <el-upload
            v-if="flag_5"
            class="upload-demo"
            drag
            action="/api/VideoWebSite/image/upload/4"
            multiple>
            <i class="el-icon-upload"/>
            <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
            <div slot="tip" class="el-upload__tip">只能上传jpeg文件，且不超过500kb</div>
          </el-upload>
          <img v-else src="http://127.0.0.1:8080/VideoWebSite/resources/carouselImage/4.jpeg" class="image">
          <div style="padding: 14px;">
            <div class="bottom clearfix">
              <el-button type="primary" mini style="float:right" @click="flag_5= !flag_5">修改</el-button>
            </div>
          </div>
        </el-card>
      </el-col >
      <el-col :span="8">
        <el-card :body-style="{ padding: '0px' }">
          <el-upload
            v-if="flag_6"
            class="upload-demo"
            drag
            action="/api/VideoWebSite/image/upload/5"
            multiple>
            <i class="el-icon-upload"/>
            <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
            <div slot="tip" class="el-upload__tip">只能上传jpeg文件，且不超过500kb</div>
          </el-upload>
          <img v-else src="http://127.0.0.1:8080/VideoWebSite/resources/carouselImage/5.jpeg" class="image">
          <div style="padding: 14px;">
            <div class="bottom clearfix">
              <el-button type="primary" mini style="float:right" @click="flag_6= !flag_6">修改</el-button>
            </div>
          </div>
        </el-card>
      </el-col >
    </el-row>
  </div>
</template>

<script>
export default {
  name: 'AdminIndex',
  data() {
    return {
      flag_1: false,
      flag_2: false,
      flag_3: false,
      flag_4: false,
      flag_5: false,
      flag_6: false,
      carouselImage: [{ url: 'http://127.0.0.1:8080/VideoWebSite/resources/carouselImage/0.jpeg' },
        { url: 'http://127.0.0.1:8080/VideoWebSite/resources/carouselImage/1.jpeg' },
        { url: 'http://127.0.0.1:8080/VideoWebSite/resources/carouselImage/2.jpeg' },
        { url: 'http://127.0.0.1:8080/VideoWebSite/resources/carouselImage/3.jpeg' },
        { url: 'http://127.0.0.1:8080/VideoWebSite/resources/carouselImage/4.jpeg' },
        { url: 'http://127.0.0.1:8080/VideoWebSite/resources/carouselImage/5.jpeg' }],
      fileList2: [{ name: 'food.jpeg',
        url: 'https://fuss10.elemecdn.com/3/63/4e7f3a15429bfda99bce42a18cdd1jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/100' },
      { name: 'food2.jpeg', url: 'https://fuss10.elemecdn.com/3/63/4e7f3a15429bfda99bce42a18cdd1jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/100' }]
    }
  },
  methods: {
    handleRemove(file, fileList) {
      console.log(file, fileList)
    },
    handlePreview(file) {
      console.log(file)
    }
  }
}
</script>

<style scoped>
 .image {
    /*width: 100%;*/
    /*max-width: 300px;*/
    /*max-height: 300px;*/
    width: 500px;
    display: block;
  }
  .clearfix:before,
  .clearfix:after {
    display: table;
    content: "";
  }

  .clearfix:after {
    clear: both
  }
</style>
