import { Component } from '@angular/core';
import { NavController, NavParams } from 'ionic-angular';

/*
  Generated class for the TimetableDetail page.

  See http://ionicframework.com/docs/v2/components/#navigation for more info on
  Ionic pages and navigation.
*/
@Component({
  selector: 'page-timetable-detail',
  templateUrl: 'timetable-detail.html'
})
export class TimetableDetailPage {

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

  constructor(public navCtrl: NavController, public navParams: NavParams) {}

  ionViewDidLoad() {
    console.log('ionViewDidLoad TimetableDetailPage');

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

}
