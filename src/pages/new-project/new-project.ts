import { Component } from '@angular/core';
import { NavController, NavParams, ViewController, ActionSheet, AlertController } from 'ionic-angular';
import { LocalNotifications } from 'ionic-native';

/*
  Generated class for the NewProject page.

  See http://ionicframework.com/docs/v2/components/#navigation for more info on
  Ionic pages and navigation.
*/
@Component({
  selector: 'page-new-project',
  templateUrl: 'new-project.html'
})
export class NewProjectPage {

	title;
    description;
    date;

  constructor(public navCtrl: NavController, public navParams: NavParams,  public alertCtrl: AlertController, public view: ViewController) {}

  saveProject(){

      let newProject = {

        title: this.title,
        description: this.description,
        date: this.date

      }

          LocalNotifications.schedule({
            title: "Project Deadline",
            text: "You are submitting a project today, don't forget",
            at: new Date(this.date - (3 * 1000 * 1800)),
            sound: null
        });

      console.log(newProject);
      this.presentAlert();
      this.view.dismiss(newProject);
    }


  close(){
    this.view.dismiss();
  }

  presentAlert() {
    let alert = this.alertCtrl.create({
      title: 'Success',
      subTitle: 'Project Saved Successfully. A reminder has been set for the submission date.',
      buttons: ['OK']
    });
    alert.present();
  }

  ionViewDidLoad() {
    console.log('ionViewDidLoad NewProjectPage');
  }

}
