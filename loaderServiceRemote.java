package gestion;

import javax.ejb.Remote;

@Remote
public interface loaderServiceRemote {
 public void addLoader(loader l) ;
 public void updateLoder(loader l);
 public void removeLoder(loader l);
 public loader findLoder(int id);
}
