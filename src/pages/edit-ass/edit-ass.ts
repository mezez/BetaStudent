import { Component } from '@angular/core';
import { NavController, NavParams, AlertController,ViewController,ActionSheet } from 'ionic-angular';
import { StatusBar, Splashscreen, SQLite, LocalNotifications } from 'ionic-native';
import { Pouch } from '../../providers/pouch';
import { AssignmentsPage } from '../assignments/assignments';

/*
  Generated class for the EditAss page.

  See http://ionicframework.com/docs/v2/components/#navigation for more info on
  Ionic pages and navigation.
*/
@Component({
  selector: 'page-edit-ass',
  templateUrl: 'edit-ass.html'
})
export class EditAssPage {

	  _id;
    _rev;
    title;
    description;
    date;

    newtitle;
    newdescription;
    newdate;
    //asspage = AssignmentsPage;


     public assignments =[];

  constructor(public navCtrl: NavController, public navParams: NavParams, public alertCtrl: AlertController, public view: ViewController,  public pouchService: Pouch) {

    console.log('ionViewDidLoad EditAssPage');
    this._id = this.navParams.get('assignment')._id;
    this._rev = this.navParams.get('assignment')._rev;
    this.title = this.navParams.get('assignment').title;
    this.description = this.navParams.get('assignment').description;
    this.date = this.navParams.get('assignment').date;
    
    this.assignments = [this._id,this._rev, this.title, this.description, this.date];
    console.log(this.assignments);
    
   

     


  	
  }

    

  saveAss(assignment){   

      let newAss = {

       

        newtitle: this.title,
        newdescription: this.description,
        newdate: this.date

      }

      //LocalNotifications.schedule({
            //title: "Assignment Deadline",
            //text: "You are submitting an assignment today, don't forget",
            //at: new Date(this.date - (3 * 1000 * 1800)),
            //sound: null
        //});


      console.log(newAss);
       if(assignment){
            this.updateAss(assignment);
          }
 
    
      //this.presentAlert();
      //this.view.dismiss(newAss);
    }

    updateAss(assignment){
     this.pouchService.updateAss({
              _id: assignment._id,
              _rev: assignment._rev,
              title: assignment.title,
              description: assignment.description,
              date: assignment.date
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
      subTitle: 'Assignment Edited Successfully. A reminder has been set for 2hours to deadline.',
      buttons: ['OK']
    });
    alert.present();
  }

  

  ionViewDidLoad() {
    console.log('ionViewDidLoad EditAssPage');
    console.log('ionViewDidLoad EditAssPage');
    this.title = this.navParams.get('assignment').title;
    this.description = this.navParams.get('assignment').description;
    this.date = this.navParams.get('assignment').date;
    
    this.assignments = [this.title, this.description, this.date];
    console.log(this.assignments);
  }



}