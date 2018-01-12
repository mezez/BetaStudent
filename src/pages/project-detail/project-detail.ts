import { Component } from '@angular/core';
import { NavController, NavParams } from 'ionic-angular';

/*
  Generated class for the ProjectDetail page.

  See http://ionicframework.com/docs/v2/components/#navigation for more info on
  Ionic pages and navigation.
*/
@Component({
  selector: 'page-project-detail',
  templateUrl: 'project-detail.html'

})
export class ProjectDetailPage {

	public title;
	public description;
  public date;

  constructor(public navCtrl: NavController, public navParams: NavParams) {

  }

  ionViewDidLoad() {
    console.log('ionViewDidLoad ProjectDetailPage');
    this.title = this.navParams.get('project').title;
    this.description = this.navParams.get('project').description;
    this.date = this.navParams.get('project').date;
  }

}
