import { Component } from '@angular/core';
import { NavController, NavParams } from 'ionic-angular';

/*
  Generated class for the ExamDetail page.

  See http://ionicframework.com/docs/v2/components/#navigation for more info on
  Ionic pages and navigation.
*/
@Component({
  selector: 'page-exam-detail',
  templateUrl: 'exam-detail.html'
})
export class ExamDetailPage {

	_id;
              _rev;
              course;
              lecturer;
              invigilators;
              date;

  constructor(public navCtrl: NavController, public navParams: NavParams) {}

  ionViewDidLoad() {
    console.log('ionViewDidLoad ExamDetailPage');
     this._id = this.navParams.get('exam')._id;
	  this._rev = this.navParams.get('exam')._rev;
	  this.course = this.navParams.get('exam').course;
	  this.lecturer = this.navParams.get('exam').lecturer;
	  this.invigilators = this.navParams.get('exam').invigilators;
	  this.date = this.navParams.get('exam').date;
  }

}
