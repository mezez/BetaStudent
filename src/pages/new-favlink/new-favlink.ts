import { Component } from '@angular/core';
import { NavController, NavParams, AlertController, ViewController, ActionSheet } from 'ionic-angular';
import { StatusBar, Splashscreen, SQLite, LocalNotifications } from 'ionic-native';
import { Favlinksp } from '../../providers/favlinksp';

/*
  Generated class for the NewFavlink page.

  See http://ionicframework.com/docs/v2/components/#navigation for more info on
  Ionic pages and navigation.
*/
@Component({
  selector: 'page-new-favlink',
  templateUrl: 'new-favlink.html'
})
export class NewFavlinkPage {

	  title;
    description;
    url;
	 

  constructor(public navCtrl: NavController, public navParams: NavParams, public alertCtrl: AlertController, public view: ViewController,  public favlinkService: Favlinksp) {


      
  }

  saveFavlink(){

      let newFavlink = {

        title: this.title,
        description: this.description,
        url: this.url

      } 


      console.log(newFavlink);

       this.favlinkService.createFavlink(newFavlink);
       this.presentAlert();
        this.close();
  }

  close(){
    this.view.dismiss();
  }


   presentAlert() {
    let alert = this.alertCtrl.create({
      title: 'Success',
      subTitle: 'Link Saved Successfully.',
      buttons: ['OK']
    });
    alert.present();
  }
  

  ionViewDidLoad() {
    console.log('ionViewDidLoad NewFavlinkPage');
     let alert = this.alertCtrl.create({
      title: 'Warning',
      subTitle: 'Url format must be www.example.com or example.com, do not attach http:// or https://.',
      buttons: ['OK']
    });
    alert.present();
  }

}


