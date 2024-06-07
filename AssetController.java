// AssetController.java

@RestController
@RequestMapping("/api/asset")
public class AssetController {

  @PostMapping("/save")
  Public ResponseEntity<byte[]> saveAsset(@RequestBody Asset asset) {

   ObjectMapper objectMapper = new ObjectMapper();
   byte[] json = objectMapper.writeValueAsBytes(asset);
   HttpHeaders headers = new HttpHeaders();
   headers.setContentType(MediaType.APPLICATION_JSON);
   headers.setContentDisposition("attachment", "assetDetails.json");
   
   return ResponseEntity.OK().headers(headers).body(json);
  }

  @PostMapping("/sign")
    public ResponseEntity<String> signAsset(@RequestParam("file") MultipartFile file){
        byte[] fileContent = file.getBytes();
        Signature signature = Signature.getInstance("SHA256withRSA");
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048);
        KeyPair keyPair = keyGen.generateKeyPair();

        signature.initSign(keyPair.getPrivate());
        signature.update(fileContent);

        byte[] signedBytes = signature.sign();
        String signedFile = Base64.getEncoder().encodeToString(signedBytes);

        return ResponseEntity.ok(signedFile);
    }

}