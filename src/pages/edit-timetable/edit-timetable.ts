import { Component } from '@angular/core';
import { NavController, NavParams, AlertController, ViewController } from 'ionic-angular';
import { Timetablep } from '../../providers/timetablep';

/*
  Generated class for the EditTimetable page.

  See http://ionicframework.com/docs/v2/components/#navigation for more info on
  Ionic pages and navigation.
*/
@Component({
  selector: 'page-edit-timetable',
  templateUrl: 'edit-timetable.html'
})
export class EditTimetablePage {

			  _id;
              _rev;
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

  constructor(public navCtrl: NavController, public navParams: NavParams , public alertCtrl: AlertController, public view: ViewController,  public timetableService: Timetablep) {}

  ionViewDidLoad() {
    console.log('ionViewDidLoad EditTimetablePage');
    this._id = this.navParams.get('timetable')._id;
	  this._rev = this.navParams.get('timetable')._rev;
	  this.title = this.navParams.get('timetable').title;
	  this.eightnine = this.navParams.get('timetable').eightnine;
	  this.nineten = this.navParams.get('timetable').nineten;
	  this.teneleven = this.navParams.get('timetable').teneleven;
	  this.eleventwelve = this.navParams.get('timetable').elevetwelve;
	  this.twelveone = this.navParams.get('timetable').twelveone;
	  this.onetwo = this.navParams.get('timetable').onetwo;
	 this.twothree = this.navParams.get('timetable').twothree;
	 this.threefour = this.navParams.get('timetable').threefour;
	  this.fourfive = this.navParams.get('timetable').fourfive;
	}

	saveTimetable(timetable){

      

      //LocalNotifications.schedule({
            //title: "Assignment Deadline",
            //text: "You are submitting an assignment today, don't forget",
            //at: new Date(this.date - (3 * 1000 * 1800)),
            //sound: null
        //});


      //console.log(newTimetable);
       if(timetable){
            this.updateTimetable(timetable);
          }
    }

   updateTimetable(timetable){
     this.timetableService.updateTimetable({
              _id: timetable._id,
              _rev: timetable._rev,
              title: timetable.title,
              eightnine: timetable.eightnine,
              nineten: timetable. nineten,
              teneleven:timetable.teneleven,
              eleventwelve:timetable.eleventwelve,
              twelveone:timetable.twelveone,
              onetwo: timetable.onetwo,
              twothree:timetable.twothree,
              threefour:timetable.threefour,
              fourfive:timetable.fourfive
            });
  }


  close(){
    this.view.dismiss();
  }

  presentAlert() {
    let alert = this.alertCtrl.create({
      title: 'Success',
      subTitle: 'Timetable Edited Successfully. A reminder has been set for 2hours to exam date.',
      buttons: ['OK']
    });
    alert.present();
  }

}
