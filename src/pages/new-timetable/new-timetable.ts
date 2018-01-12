import { Component } from '@angular/core';
import { NavController, NavParams, ViewController, AlertController } from 'ionic-angular';
import { Timetablep } from '../../providers/timetablep';
import { StatusBar, Splashscreen, SQLite, LocalNotifications } from 'ionic-native';

/*
  Generated class for the NewTimetable page.

  See http://ionicframework.com/docs/v2/components/#navigation for more info on
  Ionic pages and navigation.
*/
@Component({
  selector: 'page-new-timetable',
  templateUrl: 'new-timetable.html'
})
export class NewTimetablePage {

              title;
              eightnine;
              nineten;
              teneleven;
              eleventwelve;
              twelveone;
              onetwo;
              twothree;
              threefour;
              fourfive;

  constructor(public navCtrl: NavController, public navParams: NavParams, public alertCtrl: AlertController, public view: ViewController,  public timetableService: Timetablep) {}

  saveTimetable(){

      let newTimetable = {

          title: this.title,
          eightnine: this.eightnine,
	      nineten: this.nineten,
	      teneleven: this.teneleven,
	      eleventwelve: this.eleventwelve,
	      twelveone: this.twelveone,
	      onetwo: this.onetwo,
	      twothree: this.twothree,
	      threefour: this.threefour,
	      fourfive: this.fourfive,

      }

     // LocalNotifications.schedule({
            //title: "Exam day",
            //text: "you have an exam today, check your timetables",
           // at: new Date(this.examdate - (3 * 1000 * 1800)),
           // sound: null
        //});


      console.log(newTimetable);
      this.presentAlert();
      this.view.dismiss(newTimetable);
    }


  close(){
    this.view.dismiss();
  }


   presentAlert() {
    let alert = this.alertCtrl.create({
      title: 'Success',
      subTitle: 'Timetable Saved Successfully.',
      buttons: ['OK']
    });
    alert.present();
  }

  ionViewDidLoad() {
    console.log('ionViewDidLoad NewTimetablePage');
  }

}
