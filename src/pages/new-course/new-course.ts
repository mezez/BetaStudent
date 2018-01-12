import { Component } from '@angular/core';
import { NavController, NavParams, AlertController, ViewController, ActionSheet } from 'ionic-angular';
import { StatusBar, Splashscreen, SQLite, LocalNotifications } from 'ionic-native';

/*
  Generated class for the NewCourse page.

  See http://ionicframework.com/docs/v2/components/#navigation for more info on
  Ionic pages and navigation.
*/
@Component({
  selector: 'page-new-course',
  templateUrl: 'new-course.html'
})
export class NewCoursePage {

   title;
    lecturer;
    books;
    ldays;
    tdays;
    examdate;
	 

  constructor(public navCtrl: NavController, public navParams: NavParams, public alertCtrl: AlertController, public view: ViewController) {


      
  }

  saveCourse(){

      let newCourse = {

        title: this.title,
        lecturer: this.lecturer,
        books:this.books,
        ldays:this.ldays,
        tdays:this.tdays,
        examdate: this.examdate

      }

      LocalNotifications.schedule({
            title: "Exam day",
            text: "you have an exam today, check your courses",
            at: new Date(this.examdate - (3 * 1000 * 1800)),
            sound: null
        });


      console.log(newCourse);
      this.presentAlert();
      this.view.dismiss(newCourse);
    }


  close(){
    this.view.dismiss();
  }


   presentAlert() {
    let alert = this.alertCtrl.create({
      title: 'Success',
      subTitle: 'Course Saved Successfully. A reminder has been set for the exam date',
      buttons: ['OK']
    });
    alert.present();
  }
  

  ionViewDidLoad() {
    console.log('ionViewDidLoad NewCoursePage');
  }

}
