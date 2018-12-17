package com.masmovil.it.compensator.api.dao;

import io.reactivex.Single;

public interface VersionDao {

  Single<String> readGitProperties();

}
