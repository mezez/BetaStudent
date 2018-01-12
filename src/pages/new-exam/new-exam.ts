import { Component } from '@angular/core';
import { NavController, NavParams, ViewController, AlertController } from 'ionic-angular';
import { Examp } from '../../providers/examp';
import { StatusBar, Splashscreen, SQLite, LocalNotifications } from 'ionic-native';

/*
  Generated class for the NewExam page.

  See http://ionicframework.com/docs/v2/components/#navigation for more info on
  Ionic pages and navigation.
*/
@Component({
  selector: 'page-new-exam',
  templateUrl: 'new-exam.html'
})
export class NewExamPage {

	course;
	lecturer;
	invigilators;
	date;

  constructor(public navCtrl: NavController, public navParams: NavParams, public alertCtrl: AlertController, public view: ViewController,  public examService: Examp) {}


  saveExam(){

      let newExam = {

          course: this.course,
          lecturer: this.lecturer,
	      invigilators: this.invigilators,
	      date: this.date,
	     

      }

     // LocalNotifications.schedule({
            //title: "Exam day",
            //text: "you have an exam today, check your exams",
           // at: new Date(this.examdate - (3 * 1000 * 1800)),
           // sound: null
        //});


      console.log(newExam);
      this.presentAlert();
      this.view.dismiss(newExam);
    }


  close(){
    this.view.dismiss();
  }


   presentAlert() {
    let alert = this.alertCtrl.create({
      title: 'Success',
      subTitle: 'Exam Saved Successfully. A reminder has been set for the exam date',
      buttons: ['OK']
    });
    alert.present();
  }
  ionViewDidLoad() {
    console.log('ionViewDidLoad NewExamPage');
  }

}
