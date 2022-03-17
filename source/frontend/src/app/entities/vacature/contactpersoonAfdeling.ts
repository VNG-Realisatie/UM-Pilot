import {Emailadres} from '../shared/emailadres';
import {Telefoonnummer} from '../shared/telefoonnummer';

export interface ContactpersoonAfdeling {
  naamContactpersoonAfdeling?: string;
  telefoonnummer?: Array<Telefoonnummer>;
  emailadres?: Array<Emailadres>;
}
