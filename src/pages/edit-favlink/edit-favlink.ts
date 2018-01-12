import { Component } from '@angular/core';
import { NavController, NavParams, AlertController,ViewController,ActionSheet } from 'ionic-angular';
import { StatusBar, Splashscreen, SQLite, LocalNotifications } from 'ionic-native';
import { Favlinksp } from '../../providers/favlinksp';
import { FavlinksPage } from '../favlinks/favlinks';

/*
  Generated class for the EditFavlink page.

  See http://ionicframework.com/docs/v2/components/#navigation for more info on
  Ionic pages and navigation.
*/
@Component({
  selector: 'page-edit-favlink',
  templateUrl: 'edit-favlink.html'
})
export class EditFavlinkPage {

	  _id;
    _rev;
    title;
    description;
    url;

    newtitle;
    newdescription;
    newurl;
   


     public favlinks =[];

  constructor(public navCtrl: NavController, public navParams: NavParams, public alertCtrl: AlertController, public view: ViewController,  public favlinkService: Favlinksp) {

    console.log('ionViewDidLoad EditFavlinkPage');
    this._id = this.navParams.get('favlink')._id;
    this._rev = this.navParams.get('favlink')._rev;
    this.title = this.navParams.get('favlink').title;
    this.description = this.navParams.get('favlink').description;
    this.url = this.navParams.get('favlink').url;
    
    this.favlinks = [this._id,this._rev, this.title, this.description, this.url];
    console.log(this.favlinks);
    
   

     


  	
  }

    

  saveFavlink(favlink){   

      let newFavlink = {

       

        newtitle: this.title,
        newdescription: this.description,
        newurl: this.url

      }

      //LocalNotifications.schedule({
            //title: "Favlink Deadline",
            //text: "You are submitting an favlink today, don't forget",
            //at: new Date(this.url - (3 * 1000 * 1800)),
            //sound: null
        //});


      console.log(newFavlink);
       if(favlink){
            this.updateFavlink(favlink);
          }
 
    
      //this.presentAlert();
      //this.view.dismiss(newFavlink);
    }

    updateFavlink(favlink){
     this.favlinkService.updateFavlink({
              _id: favlink._id,
              _rev: favlink._rev,
              title: favlink.title,
              description: favlink.description,
              url: favlink.url
            });
            this.presentAlert();
            this.view.dismiss();
  }


  close(){
    this.view.dismiss();
  }

  presentAlert() {
    let alert = this.alertCtrl.create({
      title: 'Success',
      subTitle: 'Link Edited Successfully.',
      buttons: ['OK']
    });
    alert.present();
  }

  

  ionViewDidLoad() {
    console.log('ionViewDidLoad EditFavlinkPage');
    console.log('ionViewDidLoad EditFavlinkPage');
    this.title = this.navParams.get('favlink').title;
    this.description = this.navParams.get('favlink').description;
    this.url = this.navParams.get('favlink').url;
    
    this.favlinks = [this.title, this.description, this.url];
    console.log(this.favlinks);

    let alert = this.alertCtrl.create({
      title: 'Warning',
      subTitle: 'Url format must be www.example.com or example.com, do not attach http:// or https://.',
      buttons: ['OK']
    });
    alert.present();
  }



}