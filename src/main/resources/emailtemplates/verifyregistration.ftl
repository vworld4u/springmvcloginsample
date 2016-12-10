<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="utf-8"> <!-- utf-8 works for most cases -->
    <meta name="viewport" content="width=device-width"> <!-- Forcing initial-scale shouldn't be necessary -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge"> <!-- Use the latest (edge) version of IE rendering engine -->
    <meta name="x-apple-disable-message-reformatting">  <!-- Disable auto-scale in iOS 10 Mail entirely -->
    <title></title> <!-- The title tag shows in email notifications, like Android 4.4. -->

    <!-- Web Font / @font-face : BEGIN -->
	<!-- NOTE: If web fonts are not required, lines 10 - 27 can be safely removed. -->
    
    <!-- Desktop Outlook chokes on web font references and defaults to Times New Roman, so we force a safe fallback font. -->
    <!--[if mso]>
        <style>
            * {
                font-family: sans-serif !important;
            }
        </style>
    <![endif]-->
    
    <!-- All other clients get the webfont reference; some will render the font and others will silently fail to the fallbacks. More on that here: http://stylecampaign.com/blog/2015/02/webfont-support-in-email/ -->
    <!--[if !mso]><!-->
        <!-- insert web font reference, eg: <link href='https://fonts.googleapis.com/css?family=Roboto:400,700' rel='stylesheet' type='text/css'> -->
    <!--<![endif]-->

    <!-- Web Font / @font-face : END -->
    
      <!-- CSS Reset -->
    <style>

        /* What it does: Remove spaces around the email design added by some email clients. */
        /* Beware: It can remove the padding / margin and add a background color to the compose a reply window. */
        html,
        body {
            margin: 0 auto !important;
            padding: 0 !important;
            height: 100% !important;
            width: 100% !important;
        }
        
        /* What it does: Stops email clients resizing small text. */
        * {
            -ms-text-size-adjust: 100%;
            -webkit-text-size-adjust: 100%;
        }
        
        /* What is does: Centers email on Android 4.4 */
        div[style*="margin: 16px 0"] {
            margin:0 !important;
        }
        
        /* What it does: Stops Outlook from adding extra spacing to tables. */
        table,
        td {
            mso-table-lspace: 0pt !important;
            mso-table-rspace: 0pt !important;
        }
                
        /* What it does: Fixes webkit padding issue. Fix for Yahoo mail table alignment bug. Applies table-layout to the first 2 tables then removes for anything nested deeper. */
        table {
            border-spacing: 0 !important;
            border-collapse: collapse !important;
            table-layout: fixed !important;
            margin: 0 auto !important;
        }
        table table table {
            table-layout: auto; 
        }
        
        /* What it does: Uses a better rendering method when resizing images in IE. */
        img {
            -ms-interpolation-mode:bicubic;
        }
        
        /* What it does: A work-around for iOS meddling in triggered links. */
        .mobile-link--footer a,
        a[x-apple-data-detectors] {
            color:inherit !important;
            text-decoration: underline !important;
        }

        /* What it does: Prevents underlining the button text in Windows 10 */
        .button-link {
            text-decoration: none !important;
        }
      
    </style>
    
    <!-- Progressive Enhancements -->
    <style>
        
        /* What it does: Hover styles for buttons */
        .button-td,
        .button-a {
            transition: all 100ms ease-in;
        }
        .button-td:hover,
        .button-a:hover {
            background: #555555 !important;
            border-color: #555555 !important;
        }

    </style>

</head>
<body width="100%" bgcolor="#222222" style="margin: 0; mso-line-height-rule: exactly;">
    <center style="width: 100%; background: #222222;">
        <div style="display:none;font-size:1px;line-height:1px;max-height:0px;max-width:0px;opacity:0;overflow:hidden;mso-hide:all;font-family: sans-serif;">
            (Optional) Last step to activate your account at vworld4u.com
        </div>
        <div style="max-width: 600px; margin: auto;">
            <!--[if mso]>
            <table role="presentation" cellspacing="0" cellpadding="0" border="0" width="600" align="center">
				<tr>
				<td>
				<![endif]-->

				<!-- Email Header : BEGIN -->
				<table role="presentation" cellspacing="0" cellpadding="0" border="0" align="center" width="100%" style="max-width: 600px;">
					<tr>
						<td style="padding: 20px 0; text-align: center">
	<!--						<img src="http://placehold.it/200x50" width="200" height="50" alt="alt_text" border="0" style="height: auto; background: #dddddd; font-family: sans-serif; font-size: 15px; line-height: 20px; color: #555555;"> -->
							<h2 style="color: white;">Email Verification</h2>
						</td>
					</tr>
				</table>
				<!-- Email Header : END -->
				
				<!-- Email Body : BEGIN -->
				<table role="presentation" cellspacing="0" cellpadding="0" border="0" align="center" width="100%" style="max-width: 600px;">
					
					<!-- Hero Image, Flush : BEGIN -->
					<!-- <tr>
						<td bgcolor="#ffffff">
							<img src="http://placehold.it/600x300" width="600" height="" alt="alt_text" border="0" align="center" style="width: 100%; max-width: 600px; height: auto; background: #dddddd; font-family: sans-serif; font-size: 15px; line-height: 20px; color: #555555;">
						</td>
					</tr>  -->
					<!-- Hero Image, Flush : END -->

					<!-- 1 Column Text + Button : BEGIN -->
					<tr>
						<td bgcolor="#ffffff">
							<table role="presentation" cellspacing="0" cellpadding="0" border="0" width="100%">
								<tr>
									<td style="padding: 40px; font-family: sans-serif; font-size: 15px; line-height: 20px; color: #555555;">
										We are very glad that you are willing to opt for our services at MyBiller. As we send out some occasional emails to your registered email address (which are mostly related to our product and updates to our services), we need you to verify the email address once.
										<br><br>
										<!-- Button : Begin -->
										<table role="presentation" cellspacing="0" cellpadding="0" border="0" align="center" style="margin: auto;">
											<tr>
												<td style="border-radius: 3px; background: #222222; text-align: center;" class="button-td">
													<a href="${appUrl}" style="background: #222222; border: 15px solid #222222; font-family: sans-serif; font-size: 13px; line-height: 1.1; text-align: center; text-decoration: none; display: block; border-radius: 3px; font-weight: bold;" class="button-a">
														<span style="color:#ffffff;" class="button-link">&nbsp;&nbsp;&nbsp;&nbsp;Verify Me&nbsp;&nbsp;&nbsp;&nbsp;</span>
													</a>
												</td>
											</tr>
										</table>
										<!-- Button : END -->
										<br>
										We hope our services will satisfy your requirements and we wish to hear your feedback about improving our services in future.
									</td>
									</tr>
							</table>
						</td>
					</tr>
				</table>
				<!-- Email Body : END -->
			  
				<!-- Email Footer : BEGIN -->
				<table role="presentation" cellspacing="0" cellpadding="0" border="0" align="center" width="100%" style="max-width: 680px;">
					<tr>
						<td style="padding: 40px 10px;width: 100%;font-size: 12px; font-family: sans-serif; line-height:18px; text-align: center; color: #888888;">
							<webversion style="color:#cccccc; text-decoration:underline; font-weight: bold;">View as a Web Page</webversion>
							<br><br>
							Company Name<br><span class="mobile-link--footer">123 Fake Street, SpringField, OR, 97477 US</span><br><span class="mobile-link--footer">(123) 456-7890</span>
							<br><br>
							<unsubscribe style="color:#888888; text-decoration:underline;">unsubscribe</unsubscribe>
						</td>
					</tr>
				</table>
				<!-- Email Footer : END -->

				<!--[if mso]>
				</td>
				</tr>
            </table>
            <![endif]-->
        </div>
    </center>
</body>
</html>
