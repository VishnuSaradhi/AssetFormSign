<!-- asset-from.component -->

@Component({
  selector: 'app-asset',
  templateUrl: './asset-form.component.html',
  styleUrls: ['./asset-form.component.css']
})

export Class AssetForm implements ngOnInit {

 sampleForm: FormGroup;
 uploadFile: File;

@ViewChild('signPopUp') signPopUp: TemplateRef<any>;
 
 constructor(private fb:FormBuilder,private http: HttpClient, private popUp: MatDialog) {}
 ngOnInit() : void {
  this.sampleForm = this.fb.group{(
   serialNo : [''],
   name : [''],
   noOfPorts : [''],
   assetType : [''] )};
 }

 saveAsset() {

 const asset = this.sampleForm.value;
 this.http.post('/api/asset/save',asset,{responseType : 'blob'})
   .subscribe((response : Blob) =>{
      const url = window.URL.createObjectURL(response);
      const a = document.createElement('a');
      a.href = url;
      a.download = "asset.json";
      a.click();
});

 }

 openSignPopUp() {

  this.popUp.open(this.signPopUp);

 }
 
 onFileSelected(event: any) {
    this.uploadFile = event.target.files[0];
  }

 signFile() {
    const formData = new FormData();
    formData.append('file', this.uploadFile);

    this.http.post('/api/assets/sign', formData, { responseType: 'text' })
      .subscribe((response: string) => {
        const blob = new Blob([response]);
        const url = window.URL.createObjectURL(blob);
        const a = document.createElement('a');
        a.href = url;
        a.download = 'signed_file.txt';
        a.click();
      });
  }

}