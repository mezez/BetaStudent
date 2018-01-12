import { Component } from '@angular/core';
import { NavController, NavParams, AlertController, ViewController } from 'ionic-angular';
import { Examp } from '../../providers/examp';

/*
  Generated class for the EditExam page.

  See http://ionicframework.com/docs/v2/components/#navigation for more info on
  Ionic pages and navigation.
*/
@Component({
  selector: 'page-edit-exam',
  templateUrl: 'edit-exam.html'
})
export class EditExamPage {

			  _id;
              _rev;
              course;
              lecturer;
              invigilators;
              date;

  constructor(public navCtrl: NavController, public navParams: NavParams , public alertCtrl: AlertController, public view: ViewController,  public examService: Examp) {}

  ionViewDidLoad() {
    console.log('ionViewDidLoad EditExamPage');
    this._id = this.navParams.get('exam')._id;
	  this._rev = this.navParams.get('exam')._rev;
	  this.course = this.navParams.get('exam').course;
	  this.lecturer = this.navParams.get('exam').lecturer;
	  this.invigilators = this.navParams.get('exam').invigilators;
	  this.date = this.navParams.get('exam').date;
	}

	saveExam(exam){

      

      //LocalNotifications.schedule({
            //title: "Assignment Deadline",
            //text: "You are submitting an assignment today, don't forget",
            //at: new Date(this.date - (3 * 1000 * 1800)),
            //sound: null
        //});


      //console.log(newExam);
       if(exam){
            this.updateExam(exam);
          }
    }

   updateExam(exam){
     this.examService.updateExam({
              _id: exam._id,
              _rev: exam._rev,
              course: exam.course,
              lecturer: exam.lecturer,
              invigilators: exam.invigilators,
              date:exam.date,
          
            });
  }


  close(){
    this.view.dismiss();
  }

  presentAlert() {
    let alert = this.alertCtrl.create({
      title: 'Success',
      subTitle: 'Exam Edited Successfully. A reminder has been set for 2hours to exam date.',
      buttons: ['OK']
    });
    alert.present();
  }

}
