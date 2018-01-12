import { Component } from '@angular/core';
import { NavController, NavParams } from 'ionic-angular';

/*
  Generated class for the AssDetail page.

  See http://ionicframework.com/docs/v2/components/#navigation for more info on
  Ionic pages and navigation.
*/
@Component({
  selector: 'page-favlink-detail',
  templateUrl: 'favlink-detail.html'

})
export class FavlinkDetailPage {

	public title;
	public description;
  	public url;

  constructor(public navCtrl: NavController, public navParams: NavParams) {

  }

  ionViewDidLoad() {
    console.log('ionViewDidLoad AssDetailPage');
    console.log( this.navParams.get('favlink'));
    this.title = this.navParams.get('favlink').title;
    this.description = this.navParams.get('favlink').description;
    this.url = this.navParams.get('favlink').url;
  }

}
