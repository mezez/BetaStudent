import { Component } from '@angular/core';
import { NavController, NavParams, AlertController,ViewController,ActionSheet } from 'ionic-angular';
import { StatusBar, Splashscreen, SQLite, LocalNotifications } from 'ionic-native';
import { Coursesp } from '../../providers/coursesp';

/*
  Generated class for the EditCourse page.

  See http://ionicframework.com/docs/v2/components/#navigation for more info on
  Ionic pages and navigation.
*/
@Component({
  selector: 'page-edit-course',
  templateUrl: 'edit-course.html'
})
export class EditCoursePage {
	
  _id;
  _rev;
	title;
	lecturer;
    books;
    ldays;
    tdays;
    examdate;

    public courses =[];


  constructor(public navCtrl: NavController, public navParams: NavParams, public alertCtrl: AlertController, public view: ViewController,  public courseService: Coursesp) {}

  ionViewDidLoad() {
    console.log('ionViewDidLoad EditCoursePage');

    this._id = this.navParams.get('course')._id;
    this._rev = this.navParams.get('course')._rev;
    this.title = this.navParams.get('course').title;
    this.lecturer = this.navParams.get('course').lecturer;
    this.books = this.navParams.get('course').books;
    this.ldays = this.navParams.get('course').ldays;
    this.tdays = this.navParams.get('course').tdays;
    this.examdate = this.navParams.get('course').examdate;
  
  }


   saveCourse(course){

      let newCourse = {

       
        _id: this._id,
        _rev: this._rev,
        title: this.title,
        lecturer: this.lecturer,
        books: this.books,
        ldays: this.ldays,
        tdays: this.tdays,
        examdate: this.examdate,

      };

      //LocalNotifications.schedule({
            //title: "Assignment Deadline",
            //text: "You are submitting an assignment today, don't forget",
            //at: new Date(this.date - (3 * 1000 * 1800)),
            //sound: null
        //});


      console.log(newCourse);
       if(course && newCourse){
            this.updateCourse(newCourse);
          }
    }

   updateCourse(course){
     this.courseService.updateCourse({
              _id: course._id,
              _rev: course._rev,
              title: course.title,
              lecturer: course.lecturer,
              books: course.books,
              ldays:course.ldays,
              tdays:course.tdays,
              examdate:course.examdate,
            });
            this.presentAlert();
            this.close();
  }


  close(){
    this.view.dismiss();
  }

  presentAlert() {
    let alert = this.alertCtrl.create({
      title: 'Success',
      subTitle: 'Course Edited Successfully. A reminder has been set for 2hours to exam date.',
      buttons: ['OK']
    });
    alert.present();
  }
}
