import { Component } from '@angular/core';
import { NavController, NavParams, AlertController,ViewController,ActionSheet } from 'ionic-angular';
import { StatusBar, Splashscreen, SQLite, LocalNotifications } from 'ionic-native';
import { ProjectData } from '../../providers/projectdata';


/*
  Generated class for the EditProject page.

  See http://ionicframework.com/docs/v2/components/#navigation for more info on
  Ionic pages and navigation.
*/
@Component({
  selector: 'page-edit-project',
  templateUrl: 'edit-project.html'
})
export class EditProjectPage {

	 _id;
    _rev;
    title;
    description;
    date;

     public projects =[];

  constructor(public navCtrl: NavController, public navParams: NavParams, public alertCtrl: AlertController, public view: ViewController,  public projectService: ProjectData) {

  	console.log('ionViewDidLoad EditProjectPage');
    this._id = this.navParams.get('project')._id;
    this._rev = this.navParams.get('project')._rev;
    this.title = this.navParams.get('project').title;
    this.description = this.navParams.get('project').description;
    this.date = this.navParams.get('project').date;
    
    this.projects = [this._id,this._rev, this.title, this.description, this.date];
    console.log(this.projects);
  }

  saveProject(project){   

      let newProject = {

       

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


      console.log(newProject);
       if(project){
            this.updateProject(project);
          }
 
    

    }

    updateProject(project){
     this.projectService.updateProject({
              _id: project._id,
              _rev: project._rev,
              title: project.title,
              description: project.description,
              date: project.date
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
      subTitle: 'Project Edited Successfully. A reminder has been set for 2hours to deadline.',
      buttons: ['OK']
    });
    alert.present();
  }

  ionViewDidLoad() {
    
  }

}
