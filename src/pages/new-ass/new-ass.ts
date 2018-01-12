import { Component } from '@angular/core';
import { NavController, NavParams, AlertController, ViewController, ActionSheet } from 'ionic-angular';
import { StatusBar, Splashscreen, SQLite, LocalNotifications } from 'ionic-native';

/*
  Generated class for the NewAss page.

  See http://ionicframework.com/docs/v2/components/#navigation for more info on
  Ionic pages and navigation.
*/
@Component({
  selector: 'page-new-ass',
  templateUrl: 'new-ass.html'
})
export class NewAssPage {

	  title;
    description;
    date;
	 

  constructor(public navCtrl: NavController, public navParams: NavParams, public alertCtrl: AlertController, public view: ViewController) {


      
  }

  saveAss(){

      let newAss = {

        title: this.title,
        description: this.description,
        date: this.date

      }

      LocalNotifications.schedule({
            title: "Assignment Deadline",
            text: "You are submitting an assignment today, don't forget",
            at: new Date(this.date - (3 * 1000 * 1800)),
            sound: null
        });


      console.log(newAss);
      this.presentAlert();
      this.view.dismiss(newAss);
    }


  close(){
    this.view.dismiss();
  }


   presentAlert() {
    let alert = this.alertCtrl.create({
      title: 'Success',
      subTitle: 'Assignment Saved Successfully. A reminder has been set for the submission date',
      buttons: ['OK']
    });
    alert.present();
  }
  

  ionViewDidLoad() {
    console.log('ionViewDidLoad NewAssPage');
  }

}


