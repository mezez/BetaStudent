import { Component } from '@angular/core';
import { NavController, NavParams, AlertController, ModalController} from 'ionic-angular';
import { NewCoursePage } from '../new-course/new-course';
import { CourseDetailPage } from '../course-detail/course-detail';
import { EditCoursePage } from '../edit-course/edit-course';
import { CourseData } from '../../providers/course-data';
import { Coursesp } from '../../providers/coursesp';

/*
  Generated class for the Courses page.

  See http://ionicframework.com/docs/v2/components/#navigation for more info on
  Ionic pages and navigation.
*/
@Component({
  selector: 'page-courses',
  templateUrl: 'courses.html'
})
export class CoursesPage {

  newcoursepage = NewCoursePage;
  coursedetailpage = CourseDetailPage;
  editcoursepage = EditCoursePage;

  public courses = [];


  constructor(public navCtrl: NavController, public navParams: NavParams,  public alertCtrl: AlertController, public modalCtrl: ModalController, public courseService: Coursesp) {
 
  }


    addCourse(){
 
    let addModal = this.modalCtrl.create(this.newcoursepage)
 
    addModal.onDidDismiss((course) => {
 
          if(course){
            this.saveCourse(course);
          }
 
    });
 
    addModal.present();
 
  }
  editCourse(course){

 
    
    this.navCtrl.push(this.editcoursepage, {
    course: course
    });
 
 
   
 
  }

  saveCourse(course){
    this.courseService.createCourse(course);
  }
  updateCourse(course){
     this.courseService.updateCourse({
              _id: course._id,
              _rev: course._rev,
              title: course.title,
              description: course.description,
              books: course.books,
              ldays:course.ldays,
              tdays:course.tdays,
              examdate:course.examdate,
            });
  }
 
  viewCourse(course){
    this.navCtrl.push(CourseDetailPage, {
    course: course
    });
  //console.log(assignment);
}


    deleteCourse(course){
 
         this.courseService.deleteCourse(course);

         let alert = this.alertCtrl.create({
      title: 'Success',
      subTitle: 'Course Deleted.',
      buttons: ['OK']
    });
    alert.present();
      
      this.courseService.getCourse().then((data) => {
      this.courses = data;
    });
        
    }

  //newassPage(){
    //this.navCtrl.push(this.newasspage);
  //}
  coursedetailPage(){
    this.navCtrl.push(this.coursedetailpage);
  }

  ionViewDidLoad() {
    console.log('ionViewDidLoad CoursesPage');
    this.courseService.getCourse().then((data) => {
      this.courses = data;
    });
  }

}
