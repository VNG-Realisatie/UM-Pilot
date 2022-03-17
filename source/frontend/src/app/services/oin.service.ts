import {Injectable} from '@angular/core';
import {NotificationService} from './notification.service';

@Injectable({
  providedIn: 'root'
})
export class OinService {

  gemeenten: Map<string, string> = new Map<string, string>([
    ["00000001002220647000", "Utrecht"],
    ["00000001001888730000", "Hengelo"],
    ["00000001001589623000", "Enschede"],
    ["00000001001584108000", "Almelo"],
    ["00000001002313546000", "Nieuwegein"],
    ["00000001002313388000", "Houten"],
    ["00000001002313455000", "Lopik"],
    ["00000001002313832000", "IJsselstein"],
    ["00000001062000561000", "Vianen"],
    ["00000001001721860000", "Woerden"],
    ["00000001823181522000", "Bodegraven-Reeuwijk"],
    ["00000001001721628000", "Montfoort"],
    ["00000001001721689000", "Oudewater"]
  ])


  constructor(private notificationService: NotificationService) {
  }

  getGemeente(oin: string): string {
    const result = this.gemeenten.get(oin);
    if (result) {
      return result;
    } else {
      this.notificationService.showError("Gemeente not found");
      return "";
    }
  }
}
