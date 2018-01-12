import { Component } from '@angular/core';
import { NavController, NavParams } from 'ionic-angular';

/*
  Generated class for the CourseDetail page.

  See http://ionicframework.com/docs/v2/components/#navigation for more info on
  Ionic pages and navigation.
*/
@Component({
  selector: 'page-course-detail',
  templateUrl: 'course-detail.html'
})
export class CourseDetailPage {

title;
    lecturer;
    books;
    ldays;
    tdays;
    examdate;

  constructor(public navCtrl: NavController, public navParams: NavParams) {}

  ionViewDidLoad() {
    console.log('ionViewDidLoad CourseDetailPage');
    this.title = this.navParams.get('course').title;
    this.lecturer = this.navParams.get('course').lecturer;
    this.books = this.navParams.get('course').books;
    this.ldays = this.navParams.get('course').ldays;
    this.tdays = this.navParams.get('course').tdays;
    this.examdate = this.navParams.get('course').examdate;
    //console.log(this.title, this.books);
  
  }

}
