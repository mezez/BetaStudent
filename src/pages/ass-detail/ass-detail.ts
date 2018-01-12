import { Component } from '@angular/core';
import { NavController, NavParams } from 'ionic-angular';

/*
  Generated class for the AssDetail page.

  See http://ionicframework.com/docs/v2/components/#navigation for more info on
  Ionic pages and navigation.
*/
@Component({
  selector: 'page-ass-detail',
  templateUrl: 'ass-detail.html'

})
export class AssDetailPage {

	public title;
	public description;
  public date;

  constructor(public navCtrl: NavController, public navParams: NavParams) {

  }

  ionViewDidLoad() {
    console.log('ionViewDidLoad AssDetailPage');
    console.log( this.navParams.get('assignment'));
    this.title = this.navParams.get('assignment').title;
    this.description = this.navParams.get('assignment').description;
    this.date = this.navParams.get('assignment').date;
  }

}
